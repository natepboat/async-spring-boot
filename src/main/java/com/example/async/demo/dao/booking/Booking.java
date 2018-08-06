package com.example.async.demo.dao.booking;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "BOOKINGS")
@Data
@NoArgsConstructor
public class Booking {
    @Id
//    @GenericGenerator(name = "bookingIdGenerator", strategy = "com.example.async.demo.dao.booking.BookingIdGenerator")
    @GenericGenerator(
            name = "assigned-sequence",
            strategy = "com.example.async.demo.dao.booking.BookingIdGenerator"
    )
    @GeneratedValue(
            generator = "assigned-sequence"
    )
    @Column(name = "ID")
    private String id;

    @Column(name = "FIRST_NAME")
    private String firstName;
}
