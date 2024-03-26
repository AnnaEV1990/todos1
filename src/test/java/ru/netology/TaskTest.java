package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void testSimpleTaskWhenMath() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        boolean actual = simpleTask.matches("Позвонить");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testSimpleTaskWhenNotMath() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        boolean actual = simpleTask.matches("Написать");
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldGetIdEpic() {
        String[] subtasks = {"провести собеседование", "организовать встречу", "согласовать график отпусков"};
        Epic epic = new Epic(15, subtasks);
        Task task = new Task(15);
        int expected = epic.getId();
        int actual = task.getId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchInEpic() {
        SimpleTask simpleTask = new SimpleTask(6, "записаться к врачу");
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
        Task[] actual = todos.search("организовать встречу");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGetSubtasks() {

        String[] subtasks = {"провести собеседование", "организовать встречу", "согласовать график отпусков"};
        Epic epic = new Epic(15, subtasks);

        String[] expected = subtasks;
        String[] actual = epic.getSubtasks();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchInMeeting() {
        SimpleTask simpleTask = new SimpleTask(6, "записаться к врачу");

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
        Task[] expected = {meeting};
        Task[] actual = todos.search(meeting.getTopic());
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchInMeetingOne() {
        SimpleTask simpleTask = new SimpleTask(6, "записаться к врачу");

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

        Task[] expected = {meeting};
        Task[] actual = todos.search(meeting.getProject());

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchInMeetingTwo() {
        SimpleTask simpleTask = new SimpleTask(6, "записаться к врачу");
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
        Task[] expected = {};
        Task[] actual = todos.search(meeting.getStart());
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindQueryInTaskFalse() {
        Task task = new Task(15);

        boolean expected = false;
        boolean actual = task.matches("звонок");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindQueryInEpicTrue() {
        String[] subtasks = {"провести собеседование", "организовать встречу", "согласовать график отпусков"};
        Task epic = new Epic(15, subtasks);

        boolean expected = true;
        boolean actual = epic.matches("встречу");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindQueryInSimpleTaskTrue() {
        Task simpleTask = new SimpleTask(1, "Записаться к врачу");

        boolean expected = true;
        boolean actual = simpleTask.matches("Записать");

        Assertions.assertEquals(expected, actual);
    }
}
