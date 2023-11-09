package kz.example.psychonotif.controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Notificator {
    private List<Long> chats;
    private String message;
    private Optional<LocalDateTime> deadline;

    public Notificator() {
    }

    public Notificator(List<Long> chats, String message, Optional<LocalDateTime> deadline) {
        this.chats = chats;
        this.message = message;
        this.deadline = deadline;
    }

    private String constructMessage(){
        String message = "Новое уведомление!%0A" + this.message + "%0AДедлайн: ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        message += deadline.isPresent() ? deadline.get().format(formatter) : "отсутствует." ;
        return message;
    }

    public Thread notificate() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                URL url;
                try {
                    for (Long chat : chats) {
                        url = new URL(
                                "https://api.telegram.org/bot6630774756:AAFTsxAbj7uM6Woz5a7BtWkMXcrEVd6Gdt8/sendMessage" +
                                        "?chat_id=" + chat +
                                        "&text=" + constructMessage());
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);

                        DataOutputStream wr = new DataOutputStream(
                                connection.getOutputStream());
                        wr.flush();
                        wr.close();

                        int responseCode = connection.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {

                            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            String inputLine;
                            StringBuilder response = new StringBuilder();

                            while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                            }
                            in.close();

                            System.out.println("Server Response: " + response);
                        } else {
                            BufferedReader errorIn = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                            String errorInputLine;
                            StringBuilder errorResponse = new StringBuilder();

                            while ((errorInputLine = errorIn.readLine()) != null) {
                                errorResponse.append(errorInputLine);
                            }
                            errorIn.close();

                            System.out.println("Server Error Response: " + errorResponse);
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e);
                    throw new RuntimeException(e);
                } finally {
                    assert connection != null;
                    connection.disconnect();
                }
            }
        });
    }
}
