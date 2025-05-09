package com.mycompany.mytextgame;

import java.io.*;


public class FileManager {

    private static final String SAVE_FILE_PATH = System.getProperty("user.home") + File.separator + ".my_text_game_save.dat";

    public static File getSaveFile() {
        return new File(SAVE_FILE_PATH);
    }

    // 게임 데이터를 저장 파일에 기록하는 메서드
    public static boolean saveGameData(GameData data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE_PATH))) {
// 간단한 텍스트 형식으로 저장 (여기서는 캐릭터 이름만 저장)
            writer.write(data.toString());
            writer.flush();
            return true;
        } catch (IOException e) {
// 예외 발생 시 에러 메시지 출력
            e.printStackTrace();
            return false;
        }
    }

    // 세이브 파일에서 게임 데이터를 불러오는 메서드
    public static GameData loadGameData() {
        File file = getSaveFile();
        if (!file.exists()) {
// 파일이 없으면 null 반환
            return null;
        }


        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String name = reader.readLine();
            if (name != null) {
                return new GameData(name);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }
}

