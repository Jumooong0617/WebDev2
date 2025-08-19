package org.ujpogi.carfun;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private List<Car> cars;
    private final String FILE_NAME = "data/cars.csv";

    public CarService() {
        cars = new ArrayList<>();
        readFromDisk();
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public void addCar(Car car) {
        int nextId = getNextId();
        car.setCarId((long) nextId);  // assign next ID
        cars.add(car);
        writeToDisk();
    }

    public void updateCar(long id, Car updatedCar) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarId() == id) {
                cars.set(i, updatedCar);
                writeToDisk();
                break;
            }
        }
    }

    public void deleteCar(long id) {
        cars.removeIf(car -> car.getCarId() == id);
        writeToDisk();
    }

    public Car getCar(long id) {
        return cars.stream().filter(c -> c.getCarId() == id).findFirst().orElse(null);
    }

    public int getNextId() {
        return cars.stream()
                .mapToInt(car -> car.getCarId().intValue())
                .max()
                .orElse(0) + 1;
    }

    public void writeToDisk() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            // Header line (optional)
            bw.write("CarID,LicensePlateNumber,Make,Model,Year,Color,BodyType,EngineType,Transmission");
            bw.newLine();

            for (Car c : cars) {
                bw.write(String.join(",", List.of(
                        String.valueOf(c.getCarId()),
                        escape(c.getLicensePlateNumber()),
                        escape(c.getMake()),
                        escape(c.getModel()),
                        String.valueOf(c.getYear()),
                        escape(c.getColor()),
                        escape(c.getBodyType()),
                        escape(c.getEngineType()),
                        escape(c.getTransmission())
                )));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving car data: " + e.getMessage());
        }
    }

    public void readFromDisk() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Car database file not found, starting fresh.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            // Skip header line if present
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1); // keep empty strings
                if (data.length < 9) continue;  // invalid line

                Car car = new Car();
                car.setCarId(Long.parseLong(data[0]));
                car.setLicensePlateNumber(unescape(data[1]));
                car.setMake(unescape(data[2]));
                car.setModel(unescape(data[3]));
                car.setYear(Integer.parseInt(data[4]));
                car.setColor(unescape(data[5]));
                car.setBodyType(unescape(data[6]));
                car.setEngineType(unescape(data[7]));
                car.setTransmission(unescape(data[8]));

                cars.add(car);
            }
        } catch (IOException e) {
            System.out.println("Error reading car data: " + e.getMessage());
        }
    }

    private String escape(String input) {
        if (input == null) return "";
        return input.replace(",", "&#44;").replace("\n", "").replace("\r", "");
    }

    private String unescape(String input) {
        if (input == null) return "";
        return input.replace("&#44;", ",");
    }
}
