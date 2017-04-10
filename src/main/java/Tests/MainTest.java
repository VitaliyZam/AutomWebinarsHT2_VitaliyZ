package Tests;

import Utils.BaseScript;

/**
 * Created by User on 01.04.2017.
 */
public class MainTest extends BaseScript{
    public static void main(String[] args) {
        Tests tests = new Tests();

        tests.ScriptA_LoginTest();  // Скрипт А. Логин в Админ панель
        tests.ScriptB_MainMenuTest(); //Скрипт Б. Проверка работоспособности главного меню Админ панели
    }
}
