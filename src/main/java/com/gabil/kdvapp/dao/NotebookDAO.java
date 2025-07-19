package com.gabil.kdvapp.dao;

import com.gabil.kdvapp.config.SingletonDBConnection;
import com.gabil.kdvapp.dao.IDaoImplements;
import com.gabil.kdvapp.dto.NotebookDTO;
import com.gabil.kdvapp.dto.UserDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotebookDAO implements IDaoImplements<NotebookDTO> {

    private final Connection connection;

    public NotebookDAO() {
        this.connection = SingletonDBConnection.getInstance().getConnection();
    }

    @Override
    public Optional<NotebookDTO> create(NotebookDTO notebook) {
        String sql = "INSERT INTO notebook (title, content, createdDate, updatedDate, pinned, userId) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, notebook.getTitle());
            ps.setString(2, notebook.getContent());
            ps.setTimestamp(3, Timestamp.valueOf(notebook.getCreatedDate()));
            ps.setTimestamp(4, Timestamp.valueOf(notebook.getUpdatedDate()));
            ps.setBoolean(5, notebook.isPinned());
            ps.setLong(6, notebook.getUserDTO().getId());

            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        notebook.setId(rs.getLong(1));
                        return Optional.of(notebook);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<NotebookDTO>> list() {
        List<NotebookDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM notebook ORDER BY createdDate DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapToObjectDTO(rs));
            }
            return Optional.of(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<NotebookDTO> findById(int id) {
        return selectSingle("SELECT * FROM notebook WHERE id = ?", id);
    }

    @Override
    public Optional<NotebookDTO> findByName(String title) {
        return selectSingle("SELECT * FROM notebook WHERE title = ?", title);
    }

    @Override
    public Optional<NotebookDTO> update(int id, NotebookDTO updated) {
        String sql = "UPDATE notebook SET title=?, content=?, updatedDate=?, pinned=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, updated.getTitle());
            ps.setString(2, updated.getContent());
            ps.setTimestamp(3, Timestamp.valueOf(updated.getUpdatedDate()));
            ps.setBoolean(4, updated.isPinned());
            ps.setLong(5, id);
            int affected = ps.executeUpdate();
            if (affected > 0) {
                updated.setId((long) id);
                return Optional.of(updated);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<NotebookDTO> delete(int id) {
        Optional<NotebookDTO> existing = findById(id);
        if (existing.isPresent()) {
            String sql = "DELETE FROM notebook WHERE id=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                int affected = ps.executeUpdate();
                if (affected > 0) return existing;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public NotebookDTO mapToObjectDTO(ResultSet rs) throws SQLException {
        Long userIdLong = rs.getLong("userId");
        Integer userId = null;

        // Eğer userId Integer aralığındaysa, dönüştürme yapalım
        if (userIdLong <= Integer.MAX_VALUE && userIdLong >= Integer.MIN_VALUE) {
            userId = userIdLong.intValue();
        }

        return NotebookDTO.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .createdDate(rs.getTimestamp("createdDate").toLocalDateTime())
                .updatedDate(rs.getTimestamp("updatedDate").toLocalDateTime())
                .pinned(rs.getBoolean("pinned"))
                .userDTO(UserDTO.builder().id(userId).build()) // Lazy yükleme
                .build();
    }

    @Override
    public Optional<NotebookDTO> selectSingle(String sql, Object... params) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapToObjectDTO(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}