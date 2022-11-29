package com.upc.chasis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

@RestController
@RequestMapping("/")
public class ChasisController {
    @GetMapping("home")
    public String runChasis(){
        StringBuilder chasisState = new StringBuilder("");

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long start = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        int cpus = runtime.availableProcessors();
        long mmax = runtime.maxMemory() / 1024 / 1024;
        long mtotal = runtime.totalMemory() / 1024 / 1024;
        long mfree = runtime.freeMemory() / 1024 / 1024;
        File cDrive = new File("C:");
        System.out.println("Datos de JVM");
        chasisState.append("Datos de JVM ### ");

        System.out.println("CPUS:" + Integer.toString(cpus));
        chasisState.append("CPUS: ").append(Integer.toString(cpus)).append(" ### ");

        System.out.println("Memoria Maxima: " + Long.toString(mmax));
        chasisState.append("Memoria Maxima: ").append(Long.toString(mmax)).append(" ### ");

        System.out.println("Memoria total: " + Long.toString(mtotal));
        chasisState.append("Memoria total: ").append(Long.toString(mtotal)).append(" ### ");

        System.out.println("Memoria Libre: " + Long.toString(mfree));
        chasisState.append("Memoria Libre: ").append(Long.toString(mfree)).append(" ### ");
        // 1 s = 1,000 ms = 1,000,000 Âµs = 1,000,000,000 ns
        double elapseTime = (System.nanoTime() - start) / 1_000_000_000; /// *1.0e-9;
        System.out.println("Tiempo sec: " + Double.toString(elapseTime));
        chasisState.append("Tiempo sec: ").append(Double.toString(elapseTime)).append(" ### ");

        System.out.println(String.format("Espacio total c: %.2f GB", (double) cDrive.getTotalSpace() / 1073741824));
        chasisState.append(String.format("Espacio total c: %.2f GB ### ", (double) cDrive.getTotalSpace() / 1073741824));

        System.out.println(String.format("Espacio libre c: %.2f GB", (double) cDrive.getFreeSpace() / 1073741824));
        chasisState.append(String.format("Espacio libre c: %.2f GB ### ", (double) cDrive.getFreeSpace() / 1073741824));

        return chasisState.toString();
    }
}
