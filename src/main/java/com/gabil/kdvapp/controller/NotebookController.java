package com.gabil.kdvapp.controller;

import com.gabil.kdvapp.dao.NotebookDAO;
import com.gabil.kdvapp.dto.NotebookDTO;
import com.gabil.kdvapp.dto.UserDTO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDateTime;

public class NotebookController {

    // ========== FXML Bileşenleri ==========
    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentArea;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private CheckBox pinnedCheckBox;

    // ========== Kullanıcı Bilgisi ==========
    private UserDTO currentUser;

    // ========== DAO ==========
    private final NotebookDAO notebookDAO = new NotebookDAO();

    // ========== Giriş yapan kullanıcı dışarıdan set edilir ==========
    public void setUser(UserDTO user) {
        this.currentUser = user;
    }

    // ========== Kaydet butonuna basınca çalışır ==========
    @FXML
    public void saveNote() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();
        boolean pinned = pinnedCheckBox.isSelected();

        // 1. Alan boşluğu kontrolü
        if (title.isEmpty() || content.isEmpty()) {
            showAlert("Uyarı", "Başlık ve içerik boş bırakılamaz.");
            return;
        }

        // 2. Kullanıcı kontrolü
        if (currentUser == null) {
            showAlert("Hata", "Kullanıcı bilgisi alınamadı.");
            return;
        }

        // 3. DTO oluşturma
        NotebookDTO notebook = NotebookDTO.builder()
                .title(title)
                .content(content)
                .pinned(pinned)
                .createdDate(LocalDateTime.now())
                .userDTO(currentUser)
                .build();

        // 4. Veritabanına kaydet
        notebookDAO.create(notebook);

        // 5. Temizle ve bilgi ver
        showAlert("Başarılı", "Not kaydedildi.");
        titleField.clear();
        contentArea.clear();
        pinnedCheckBox.setSelected(false);
    }

    // ========== Uyarı gösterme metodu ==========
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
