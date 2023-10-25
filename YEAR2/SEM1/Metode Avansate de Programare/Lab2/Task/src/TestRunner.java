import Container.Strategy;
import Model.MessageTask;
import Model.SortingMethod;
import Model.Task;
import Model.SortingTask;
import Runners.DelayTaskRunner;
import Runners.PrinterTaskRunner;
import Runners.StrategyTaskRunner;
import Runners.TaskRunner;
import Utils.Constants;

import java.lang.module.Configuration;
import java.time.LocalDateTime;

public class TestRunner {
    private static MessageTask[] getMessages(){
        MessageTask feedbacklab= new MessageTask("1","Feedback lab1",
                "Ai obtinut 9.60","Gigi","Ana",
                LocalDateTime.of(2018,3,27,9,29));

        MessageTask feedbacksem=new MessageTask("5","Feedback lab1",
                "Ai obtinut 7.70","Gigi","George",
                LocalDateTime.of(2021,2,17,12,2));

        MessageTask feedbackcurs= new MessageTask("4","Feedback lab1",
                "Ai obtinut 4.50","Gigi","Maria",
                LocalDateTime.of(2022,8,14,8,20));
        return new MessageTask[]{feedbacklab,feedbacksem,feedbackcurs};
    }

    public static void run(String[] args) {
        MessageTask[] messages = getMessages();


        int[] integers_vector =new int[]{10,9,8,7,6,5,4,3,2,1,0};
        new SortingTask(integers_vector).sort(SortingMethod.BUBBLE);
        System.out.println();
        new SortingTask(integers_vector).sort(SortingMethod.QUICK);
        System.out.println();


        TaskRunner taskRunner = new StrategyTaskRunner(Strategy.valueOf(args[0]));
        taskRunner.addTask(messages[0]);
        taskRunner.addTask(messages[1]);
        taskRunner.addTask(messages[2]);
        taskRunner.executeAll();
        System.out.println("\n");

        DelayTaskRunner delayTaskRunner = new DelayTaskRunner(new StrategyTaskRunner(Strategy.valueOf(args[0])));
        delayTaskRunner.addTask(messages[0]);
        delayTaskRunner.addTask(messages[1]);
        delayTaskRunner.addTask(messages[2]);
        delayTaskRunner.executeAll();
        System.out.println("\n");

        PrinterTaskRunner printerTaskRunner = new PrinterTaskRunner(new StrategyTaskRunner(Strategy.valueOf(args[0])));
        printerTaskRunner.addTask(messages[0]);
        printerTaskRunner.addTask(messages[1]);
        printerTaskRunner.addTask(messages[2]);
        printerTaskRunner.executeAll();
        System.out.println("\n");

        //ex14
        PrinterTaskRunner printerTaskRunner1 = new PrinterTaskRunner(new DelayTaskRunner(new StrategyTaskRunner(Strategy.valueOf(args[0]))));
        printerTaskRunner1.addTask(messages[0]);
        printerTaskRunner1.addTask(messages[1]);
        printerTaskRunner1.addTask(messages[2]);
        printerTaskRunner1.executeAll();
        System.out.println("\n");
}
}
