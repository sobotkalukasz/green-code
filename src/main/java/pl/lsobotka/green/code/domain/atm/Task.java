package pl.lsobotka.green.code.domain.atm;

import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

import org.springframework.lang.NonNull;

import pl.lsobotka.green.code.interfaces.rest.atm.dto.TaskDto;

public class Task implements Comparable<Task> {

    private final Atm atm;
    private final AtmRequestType requestType;

    public Task(Atm atm, AtmRequestType requestType) {
        this.atm = atm;
        this.requestType = requestType;
    }

    public static Task from(final TaskDto task) {
        final Atm atm = new Atm(new RegionCode(task.region()), new AtmId(task.atmId()));
        return new Task(atm, task.requestType());
    }

    public Atm getAtm() {
        return atm;
    }

    public RegionCode getRegionCode() {
        return atm.getRegionCode();
    }

    public AtmRequestType getRequestType() {
        return requestType;
    }

    @Override
    public int compareTo(@NonNull final Task o) {
        return Comparator.comparing(Task::getRegionCode)
                .thenComparing((t1, t2) -> t1.getRequestType().compareByImportanceLevel(t2.getRequestType()))
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(atm, task.atm) && requestType == task.requestType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(atm, requestType);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Task.class.getSimpleName() + "[", "]").add("atm=" + atm)
                .add("requestType=" + requestType)
                .toString();
    }
}
