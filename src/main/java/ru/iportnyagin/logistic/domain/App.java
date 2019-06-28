package ru.iportnyagin.logistic.domain;

import org.hibernate.Session;

/**
 * App.
 *
 * @author portnyagin
 */
public class App {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            ScheduleItem scheduleItem = new ScheduleItem();
            scheduleItem.setDay(1);
            scheduleItem.setHour(1);
            scheduleItem.setDuration(1);
            session.save(scheduleItem);

            Branch branch = new Branch();
            branch.setBranchId("100");
            branch.getScheduleItems().add(scheduleItem);
            session.save(branch);

            session.getTransaction().commit();
        }
    }

}
