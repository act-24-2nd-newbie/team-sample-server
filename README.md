# Team Sample Server

팀 프로젝트 샘플 서버입니다.
다음과 같은 기술 스택을 사용합니다.

* Java 17
* Spring boot 3.3.4
* Spring Data JPA
* 로컬DB : H2 Database
* 서버DB : Postrgresql

## 환경 설정

Profile을 사용하여 개발용/운영용 설정을 나누어 두었습니다.

* 개발용 설정 (application.dev.yml)
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:sns  # 필요하면 수정하세요!
    username: sa
```

* 운영용 설정 (application.prod.yml)
```yaml
spring:
  datasource:
    url: jdbc:postgresql://[::1]:5432/dev  # 주소는 운영용 주소로 수정될 예정입니다.
    username: team1
    password: <PASSWORD>
  jpa:
    hibernate:
      ddl-auto: validate
  h2:
    console:
      enabled: off
  devtools:
    livereload:
      enabled: false
```

## 로컬에서 실행하기
* local 실행용 프로파일이 생성되어 있기 때문에 실행시 파라메터를 추가해야 합니다.
* IDEA를 사용중이라면 **활성화된 프로파일** 란에 **local**이라고 적어주세요.
```shell
-Dspring.profiles.active=local
```

## 테이블 만들기
H2와 Postgres간 ID호환을 위해 다음과 같이 ID를 설정해야 합니다.

* SQL
```yaml
create table users (
  id bigserial primary key,
  # ... 다른 컬럼들 ...
);
```
* User.java
```java
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {
    @Id
    // 이름은 <tablename>_<columnname>_seq
    @SequenceGenerator(name = "users_id_seq", allocationSize = 1)
    // generator 는 상단 generator와 맞춰주세요
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_id_seq")
    private Long id;
    
    // ... 추가 property ...
}
```
