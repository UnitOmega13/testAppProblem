package unit;

import unit.model.Query;
import unit.model.WTimeline;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    private static final List<Query> queryList = new ArrayList<>();
    private static final List<WTimeline> wTimelineList = new ArrayList<>();

    public static void main(String[] args) {
        int number;
        System.out.print("Input arguments(1 - for reading from file, 2 - for reading from console): ");
        number = scan.nextInt();
        if (number == 2) {
            readFromConsole();
        } else {
            readFromFile();
        }
        if (!queryList.isEmpty() & !wTimelineList.isEmpty()) {
            writeData();
        } else {
            System.out.print("No Data!");
        }
    }

    private static void readFromFile() {
        System.out.print("Input name of text file with extension and it path, like (C:/text.txt): ");
        scan.nextLine();
        String fileName = scan.nextLine();
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            scan = new Scanner(fileReader);
            int numberOfLines = scan.nextInt();
            for (int index = 0; index <= numberOfLines; index++) {
                String line = scan.nextLine();
                addData(line);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.print("Error, file not found.");
        } catch (IOException e) {
            System.out.print("Error reading data.");
        }
    }

    private static void readFromConsole() {
        System.out.print("Input number of lines: ");
        int numberOfLines = scan.nextInt();
        scan.nextLine();
        for (int index = 1; index <= numberOfLines; index++) {
            System.out.print("Input line number " + index + " : ");
            String line = scan.nextLine();
            addData(line);
        }
    }

    private static void addData(String line) {
        String[] arguments = line.split(" ");
        LocalDate dateEnd;
        if (arguments[0].equals("C")) {
            String[] dateData = arguments[4].split("\\.");
            LocalDate date = LocalDate.of(Integer.parseInt(dateData[2]), Integer.parseInt(dateData[1]), Integer.parseInt(dateData[0]));
            WTimeline wTimeline = new WTimeline(arguments[1], arguments[2], arguments[3], date, arguments[5]);
            wTimelineList.add(wTimeline);
        } else if (arguments[0].equals("D")) {
            String[] dates = arguments[4].split("-");
            String[] dateDataStart = dates[0].split("\\.");
            LocalDate dateStart = LocalDate.of(Integer.parseInt(dateDataStart[2]),
                    Integer.parseInt(dateDataStart[1]), Integer.parseInt(dateDataStart[0]));
            if (!(arguments[4].length() <= 10)) {
                String[] dateDataEnd = dates[1].split("\\.");
                dateEnd = LocalDate.of(Integer.parseInt(dateDataEnd[2]),
                        Integer.parseInt(dateDataEnd[1]), Integer.parseInt(dateDataEnd[0]));
            } else {
                dateEnd = dateStart;
            }
            String[] serviceId = arguments[1].split("\\.");
            String[] questionTypeId = arguments[2].split("\\.");
            queryList.add(new Query(serviceId[0], questionTypeId[0], arguments[3], dateStart, dateEnd));
        }
    }

    private static void writeData() {
        int time = 0;
        List<WTimeline> result = new ArrayList<>();
        for (int queryIndex = 0; queryIndex <= queryList.size() - 1; queryIndex++) {
            for (int wTimelineIndex = 0; wTimelineIndex <= wTimelineList.size() - 1; wTimelineIndex++) {
                int finalQueryIndex = queryIndex;
                if (queryList.get(finalQueryIndex).getServiceId().equals("*")) {
                    result = wTimelineList.stream()
                            .filter(x -> x.getAnswerType().equals(queryList.get(finalQueryIndex).getAnswerType()) &&
                                    x.getQuestionTypeId().startsWith(queryList.get(finalQueryIndex).getQuestionTypeId()) &&
                                    (x.getDate().isAfter(queryList.get(finalQueryIndex).getStart()) &&
                                            (x.getDate().isBefore(queryList.get(finalQueryIndex).getEnd()))))
                            .collect(Collectors.toList());
                } else if (queryList.get(finalQueryIndex).getQuestionTypeId().equals("*")) {
                    result = wTimelineList.stream()
                            .filter(x -> x.getAnswerType().equals(queryList.get(finalQueryIndex).getAnswerType()) &&
                                    x.getServiceId().startsWith(queryList.get(finalQueryIndex).getServiceId()) &&
                                    (x.getDate().isAfter(queryList.get(finalQueryIndex).getStart()) &&
                                            (x.getDate().isBefore(queryList.get(finalQueryIndex).getEnd()))))
                            .collect(Collectors.toList());
                } else {
                    result = wTimelineList.stream()
                            .filter(x -> x.getAnswerType().equals(queryList.get(finalQueryIndex).getAnswerType()) &&
                                    x.getQuestionTypeId().startsWith(queryList.get(finalQueryIndex).getQuestionTypeId()) &&
                                    x.getServiceId().startsWith(queryList.get(finalQueryIndex).getServiceId()) &&
                                    (x.getDate().isAfter(queryList.get(finalQueryIndex).getStart()) &&
                                            (x.getDate().isBefore(queryList.get(finalQueryIndex).getEnd()))))
                            .collect(Collectors.toList());
                }
            }
            if (!result.isEmpty()) {
                for (int index = 0; index <= result.size() - 1; index++) {
                    time = time + Integer.parseInt(result.get(index).getTime());
                }
                int resuleTime = Math.round(time / result.size());
                System.out.println(resuleTime);
            } else {
                System.out.println("-");
            }
            result.clear();
            time = 0;
        }
    }
}
