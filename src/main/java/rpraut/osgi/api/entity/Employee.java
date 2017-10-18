package rpraut.osgi.api.entity;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * The type Employee.
 */
public class Employee implements Portable {

    private Integer id;
    private String name;
    private int age;
    private LocalDateTime localDateTime;
    private Calendar calendar;
    private Date date;


    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * Sets local date time.
     *
     * @param localDateTime the local date time
     */
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * Gets calendar.
     *
     * @return the calendar
     */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * Sets calendar.
     *
     * @param calendar the calendar
     */
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int getFactoryId() {
        return 0;
    }

    @Override
    public int getClassId() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(localDateTime, employee.localDateTime) &&
                Objects.equals(calendar, employee.calendar) &&
                Objects.equals(date, employee.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, localDateTime, calendar, date);
    }

    @Override
    public void writePortable(PortableWriter out) throws IOException {
        out.writeUTF("name", name);
        out.writeInt("age", age);
        ObjectDataOutput rawDataOutput = out.getRawDataOutput();
        rawDataOutput.writeObject(id);
        rawDataOutput.writeObject(localDateTime);
        rawDataOutput.writeObject(calendar);
        rawDataOutput.writeObject(date);
    }

    @Override
    public void readPortable(PortableReader in) throws IOException {
        this.name = in.readUTF("name");
        this.age = in.readInt("age");
        ObjectDataInput rawDataInput = in.getRawDataInput();
        this.id = rawDataInput.readObject();
        this.localDateTime = rawDataInput.readObject();
        this.calendar = rawDataInput.readObject();
        this.date = rawDataInput.readObject();
    }

}
