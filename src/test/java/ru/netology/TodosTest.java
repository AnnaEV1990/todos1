package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchWhenNotTasksFound() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить хлеб");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        Task[] expected = {};
        Task[] actual = todos.search("Позвонить");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchWhenTasksFoundEpic() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить хлеб");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        Task[] expected = {epic};
        Task[] actual = todos.search("Молоко");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGetId() {

        String[] subtasks = {"провести собеседование", "организовать встречу", "согласовать график отпусков"};
        Epic epic = new Epic(15, subtasks);
        Task task = new Task(15);

        int expected = epic.getId();
        int actual = task.getId();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchInTodos() {
        SimpleTask simpleTask = new SimpleTask(1, "Записаться к врачу");

        String[] subtasks = {"провести собеседование", "организовать встречу", "согласовать график отпусков"};
        Epic epic = new Epic(15, subtasks);

        Meeting meeting = new Meeting(
                18,
                "Сделка ПФИ",
                "Внедрение нового вида сделки",
                "в 14:00 по НСК"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.search(" ");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchInSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(1, "Записаться к врачу");

        String[] subtasks = {"провести собеседование", "организовать встречу", "согласовать график отпусков", "Закрыть задачу"};
        Epic epic = new Epic(15, subtasks);

        Meeting meeting = new Meeting(
                18,
                "Сделка ПФИ",
                "Внедрение нового вида сделки",
                "в 14:00 по НСК"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask};
        Task[] actual = todos.search("к врачу");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchInEpic() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить врачу");

        String[] subtasks = {"провести собеседование", "организовать встречу", "согласовать график отпусков"};
        Epic epic = new Epic(15, subtasks);

        Meeting meeting = new Meeting(
                18,
                "Сделка ПФИ",
                "Внедрение нового вида сделки",
                "в 14:00 по НСК"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {epic};
        Task[] actual = todos.search("встречу");

        Assertions.assertArrayEquals(expected, actual);
    }
}



