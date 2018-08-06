package com.example.async.demo.dao.booking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void testSaveOne() {
        Booking booking = new Booking();
        booking.setFirstName("booking");
        bookingRepository.saveAndFlush(booking);
        Booking result = bookingRepository.findOne("BK0000");
        Assert.assertEquals("booking", result.getFirstName());
    }

    @Test
    public void testSaveMultiple() {
        Booking b1 = new Booking();
        b1.setFirstName("one");
        Booking b2 = new Booking();
        b2.setFirstName("two");
        Booking b3 = new Booking();
        b3.setFirstName("three");
        Booking b4 = new Booking();
        b4.setFirstName("four");

        bookingRepository.save(Arrays.asList(b1, b2, b3 ,b4));

        Booking br1 = bookingRepository.findOne("BK0000");
        Booking br2 = bookingRepository.findOne("BK0001");
        Booking br3 = bookingRepository.findOne("BK0002");
        Booking br4 = bookingRepository.findOne("BK0003");
        Assert.assertEquals("one", br1.getFirstName());
        Assert.assertEquals("two", br2.getFirstName());
        Assert.assertEquals("three", br3.getFirstName());
        Assert.assertEquals("four", br4.getFirstName());
    }
}
