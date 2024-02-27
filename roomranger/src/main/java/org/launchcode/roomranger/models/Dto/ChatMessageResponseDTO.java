package org.launchcode.roomranger.models.Dto;




public class ChatMessageResponseDTO {

    private String fromName;
    private String text;



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }


}