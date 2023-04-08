package pl.lsobotka.green.code.application.atm;

import java.util.*;

import pl.lsobotka.green.code.domain.atm.Atm;
import pl.lsobotka.green.code.domain.atm.Task;

public class TaskOrderProcessor {

    public static List<Task> orderTasks(final List<Task> tasks) {
        final List<Task> uniqueTasks = new ArrayList<>();
        final Set<Atm> exclude = new HashSet<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task possibleTask = tasks.get(i);

            if (!exclude.contains(possibleTask.getAtm())) {
                for (int j = i + 1; j < tasks.size(); j++) {
                    final Task nextTask = tasks.get(j);
                    if (possibleTask.getAtm().equals(nextTask.getAtm())) {
                        exclude.add(nextTask.getAtm());

                        possibleTask =
                                possibleTask.getRequestType().compareByImportanceLevel(nextTask.getRequestType()) <= 0 ?
                                        possibleTask :
                                        nextTask;
                    }
                }
                uniqueTasks.add(possibleTask);
            }
        }

        Collections.sort(uniqueTasks);
        return uniqueTasks;
    }
}
