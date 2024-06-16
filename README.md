# Kotlin Spring Boot Hexagonal Architecture

## 개요

![hexagonal-architecture.png](doc/.image/hexagonal-architecture.png)

## 설계

* `Primary-adapter == endpoint` 와 동일하다.
* `Seconadry-adpater == persistence` 와 동일하다.
* 클래스 네이밍 룰은 누구나 이해하기 쉽도록 `Read~~`, `Write~~` 로 작성 한다.
* `Use-case` 와 `Service` 는 1:1 관계를 가지게 하여 인터페이스 분리 원칙을 지키도록 한다.
* 도메인에서는 한개 엔티티를 지정하여 `Bounded-context` 내 가능한 한개의 루트 엔티티를 가지도록 한다.
* 가능하다면 도메인 속성은 VO로 선언하여 관리하고 검증을 하도록 한다.

### 추가

* 어댑터 경우 지금 구성에서 테스트 필요를 못 느끼겠어서 우선 삭제함.
    * 모킹을 하지 않고 해서?
    * 아니면 구조상 그런건가?
