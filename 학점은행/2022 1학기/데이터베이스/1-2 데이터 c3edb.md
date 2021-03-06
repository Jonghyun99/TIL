# 1-2. 데이터베이스 시스템

## 데이터베이스 관리 시스템

### **데이터베이스 특징**

- 질의를 실시간으로 처리하며 응답할 수 있어야 한다(실시간 접근성)
- 삽입/삭제/갱신에 의해 계속 변화며(계속 변화)
- 여러 사용자가 동시에 데이터를 공유할 수 있다(동시 공유)
- 데이터 내용에 따라 참조할 수 있다(내용기반 참조)

### **데이터베이스 관리 시스템 개념**

Case1. A사 직원은 캐비닛 파일 박스에 서류를 담아 놓고 필요할 때 마다 꺼내 오는 일을 한다.

캐비닛 > 비서 > 파일 박스 > 서류

Case2. 컴퓨터에 기반을 둔 데이터베이스 환경에는 컴퓨터 소프트웨어가 처리한다.

DBMS > 데이터베이스 > 데이터

### **데이터베이스 관리 시스템 등장 배경**

파일 시스템은 컴퓨터 보조기억장치 파일(file)에 데이터를 저장함

- 여기서 파일은 순차적인 레코드로 구성되어 있고, 한 레코드는 연관된 필드로 구성됨
- 1960년대 부터 사용된 파일 시스템은 프로그램 별로 파일을 일대일로 대응해서 사용하기 때문에 데이터 종속, 중복 문제가 발생함

### **데이터베이스 관리 시스템이란?**

사용자 또는 응용 프로그램과 데이터베이스 사이에 위치하여 데이터베이스를 공유할 수 있도록 관리해 주는 소프트웨어

| 구분 | DBMS | 파일시스템 |
| --- | --- | --- |
| 데이터 종속성 | 독립성 보장 | 종속성 발생 |
| 데이터 중복성 | 중복 최소화 | 중복 발생 |
| 데이터 공유성 | 공유 가능 | 공유 불가 |
| 데이터 일관성 | 일관성 보장 | 일관성 불가 |
| 데이터 병행성 | 병행성 제공 | 병행성 불가 |
| 생산성 | 고 | 저 |
| 자원 수요 | 대 | 소 |
| 효율성 | 저 | 고 |

### **데이터베이스 관리 시스템의 종류**

네트워크 DBMS

계층 DBMS

관계 DBMS (SQL)

No SQL

### **데이터베이스 관리 시스템의 구성요소**

사용자: 데이터언어

응용프로그램: 데이터 언어

DBMS: 접근관리, 질의처리

DB: 데이터 사전, 저장된 DB

### 데이터베이스 시스템 사용자

최종사용자(일반사용자): 응용 프로그램을 사용하는 사람

응용프로그래머: DBMS와 욘동된 응용 프로그램을 사용하는 사람

DataBase관리자: DBMs 및 이와 관련된 하드웨어 또는 소프트웨어을 관리 감독하는 사람

### **데이터 언어**

- 데이터 정의어: 데이터베이스를 정의하거나 그 정의를 수정할 목적으로 사용
- 데이터 조작어: 데이터를 검색, 삭제, 변경하는데 사용
- 데이터 제어어: 데이터베이스를 올바르게 공용하게 하면서 정확하게 유지하는데 사용