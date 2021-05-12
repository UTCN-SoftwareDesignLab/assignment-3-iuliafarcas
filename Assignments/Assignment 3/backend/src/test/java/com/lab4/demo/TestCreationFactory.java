package com.lab4.demo;

import com.lab4.demo.clinic.model.Consultation;
import com.lab4.demo.clinic.model.Patient;
import com.lab4.demo.clinic.model.dto.ConsultationDTO;
import com.lab4.demo.clinic.model.dto.PatientDTO;
import com.lab4.demo.user.dto.UserListDTO;
import com.lab4.demo.user.model.User;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class TestCreationFactory {

    @SuppressWarnings("all")
    public static <T> List<T> listOf(Class cls) {
        return listOf(cls, (Object[]) null);
    }

    @SuppressWarnings("all")
    public static <T> List<T> listOf(Class cls, Object... parameters) {
        int nrElements = new Random().nextInt(10) + 5;
        Supplier<?> supplier;

        if (cls.equals(UserListDTO.class)) {
            supplier = TestCreationFactory::newUserListDTO;
        } else if (cls.equals(Patient.class)) {
            supplier = TestCreationFactory::newPatient;
        }else if (cls.equals(PatientDTO.class)) {
            supplier = TestCreationFactory::newPatientDTO;
        }else if (cls.equals(Consultation.class)) {
            supplier = TestCreationFactory::newConsultation;
        }else if (cls.equals(ConsultationDTO.class)) {
            supplier = TestCreationFactory::newConsultationDTO;
        }else {
            supplier = () -> new String("You failed.");
        }

        Supplier<?> finalSupplier = supplier;
        return IntStream.range(0, nrElements).mapToObj(i ->
                (T) finalSupplier.get()
        ).collect(Collectors.toSet()) // remove duplicates in case of Long for example
                .stream().collect(toList());
    }

    private static UserListDTO newUserListDTO() {
        return UserListDTO.builder()
                .id(randomLong())
                .username(randomString())
                .email(randomEmail())
                .build();
    }

    private static Patient newPatient(){
        return Patient.builder()
                .name(randomString())
                .card_id(randomString())
                .cnp(randomString())
                .birth_date(new Date(randomLong()))
                .address(randomString())
                .build();
    }

    private static PatientDTO newPatientDTO(){
        return PatientDTO.builder()
                .name(randomString())
                .card_id(randomString())
                .cnp(randomString())
                .birth_date(new Date(randomLong()))
                .address(randomString())
                .build();
    }

    private static Consultation newConsultation(){
        Patient patient = newPatient();
        User doctor = User.builder()
                .email(randomEmail())
                .username(randomString())
                .password(randomString())
                .build();

        return Consultation.builder()
                .doctor(doctor)
                .patient(patient)
                .startDate(new Date(randomLong()))
                .endDate(new Date(randomLong()))
                .details(randomString())
                .build();
    }

    private static ConsultationDTO newConsultationDTO(){
        Patient patient = newPatient();
        User doctor = User.builder()
                .email(randomEmail())
                .username(randomString())
                .password(randomString())
                .build();

        return ConsultationDTO.builder()
                .doctor_id(doctor.getId())
                .patient_id(patient.getId())
                .start_date(new Date(randomLong()))
                .end_date(new Date(randomLong()))
                .details(randomString())
                .build();
    }



    public static String randomEmail() {
        return randomString() + "@" + randomString() + ".com";
    }

    public static byte[] randomBytes() {
        byte[] bytes = new byte[Math.toIntExact(randomLong())];
        new Random().nextBytes(bytes);
        return bytes;
    }

    public static long randomLong() {
        return new Random().nextInt(1999);
    }

    public static Boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    public static int randomBoundedInt(int upperBound) {
        return new Random().nextInt(upperBound);
    }

    public static String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
