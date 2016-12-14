package temp;

/**
 * Created by justin on 12/8/16.
 */
public class Appointment {

    private String appointmentId;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {

        System.out.println("+++++ Setting appointment ID in bean: " + appointmentId);

        this.appointmentId = appointmentId;
    }
}
