import java.io.*;
import java.util.*;

// 1. Country 추상 클래스 생성
// 이 클래스는 모든 국가 관련 클래스의 기본 정보를 정의합니다.
abstract class Country {
    protected String name; // 국가 이름
    protected int population; // 인구 수
    protected double area; // 면적

    // 생성자: 국가 이름, 인구 수, 면적 초기화
    public Country(String name, int population, double area) {
        this.name = name;
        this.population = population;
        this.area = area;
    }

    // 국가 정보를 출력하는 추상 메서드
    public abstract void displayInfo();
}

// 2. State 클래스 (Country 상속)
// 특정 국가의 주 정보를 관리하는 클래스
class State extends Country {
    private String stateName; // 주 이름

    public State(String countryName, int population, double area, String stateName) {
        super(countryName, population, area); // 부모 클래스의 생성자 호출
        this.stateName = stateName; // 주 이름 초기화
    }

    // 주 정보를 출력
    @Override
    public void displayInfo() {
        System.out.println("국가: " + name + ", 주: " + stateName + 
                           ", 인구: " + population + ", 면적: " + area + "㎢");
    }
}

// 3. Nation 클래스 (Country 상속)
// 특정 대륙에 속한 국가 정보를 관리하는 클래스
class Nation extends Country {
    private String continent; // 대륙 이름

    public Nation(String name, int population, double area, String continent) {
        super(name, population, area); // 부모 클래스의 생성자 호출
        this.continent = continent; // 대륙 이름 초기화
    }

    // 국가 및 대륙 정보를 출력
    @Override
    public void displayInfo() {
        System.out.println("대륙: " + continent + ", 국가: " + name + 
                           ", 인구: " + population + ", 면적: " + area + "㎢");
    }
}

// 4. Reportable 인터페이스
// 보고서를 생성하는 메서드를 정의하는 인터페이스
interface Reportable {
    void generateReport(String filename) throws IOException;
}

// 5. Main 클래스
public class Main {
    public static void main(String[] args) {
        // 6. 국가 및 주 데이터를 저장하기 위한 ArrayList 사용
        List<Country> countries = new ArrayList<>();

        // 데이터 추가
        countries.add(new Nation("Korea", 50000000, 100000, "Asia")); // 한국
        countries.add(new State("USA", 331000000, 9833520, "California")); // 미국 - 캘리포니아

        // 7. 모든 국가 정보 출력
        System.out.println("국가 정보 출력:");
        for (Country country : countries) {
            country.displayInfo(); // 다형성 사용
        }

        // 8. 파일 입출력: 국가 정보를 파일에 저장
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("countries.txt"))) {
            for (Country country : countries) {
                writer.write(country.name + "," + country.population + "," + country.area + "\n");
            }
            System.out.println("국가 데이터가 파일에 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
        }
    }
}