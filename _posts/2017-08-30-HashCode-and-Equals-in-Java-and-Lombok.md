---
layout: post
title: "HashCode() and equals() Java Object's methods with Lombok help"
date: 2017-08-30
comments: true
category: articles
excerpt_separator: <!--more-->
---


In this post I would like to write about how bad wrong defined hashCode can affect HashMap performance.


<!--more-->


To provide performance tests, I wrote two Junit tests. Both tests with testing classes can be accessed in [repo](https://github.com/Pszemo230/JavaExercises/tree/master/hashCodeAndEquals) as maven project.

Objects which will be inserted to HashMap are User class but one will have wrong hashCode() method (returning '-1' for every object).
In order to reduce implementation time, I used [Lombok](https://projectlombok.org/) library and it's `@Data` annotation.


@Data annotation helped in reducing lines of code in both Users classes. It automatically generated:
- all getters and setters for fields
- toString() method
- equals(Object o) method
- public constructor without parameters
- canEqual(Object other) method


Whole class UserWithCorrectHashCode code can be then written as follows:
```java

@Data
public class UserWithCorrectHashCode implements User{

  private String id;

  private String username;

  private Integer age;

}
```

In this project I used Intellij IDEA IDE which have great Lombok support afrter installing Lombok [plugin](https://plugins.jetbrains.com/plugin/6317-lombok-plugin).
It lets user to delombok annotations as showed below:


![Lombok plugin options](https://github.com/Pszemo230/Pszemo230.github.io/blob/master/assets/delombok.PNG?raw=true "Lombok plugin options")


After delombok UserWithCorrectHashCode class code looks like this:


```java
public class UserWithCorrectHashCode implements User{

  private String id;

  private String username;

  private Integer age;

  public UserWithCorrectHashCode() {
  }

  public String getId() {
    return this.id;
  }

  public String getUsername() {
    return this.username;
  }

  public Integer getAge() {
    return this.age;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof UserWithCorrectHashCode)) {
      return false;
    }
    final UserWithCorrectHashCode other = (UserWithCorrectHashCode) o;
    if (!other.canEqual((Object) this)) {
      return false;
    }
    final Object this$id = this.getId();
    final Object other$id = other.getId();
    if (this$id == null ? other$id != null : !this$id.equals(other$id)) {
      return false;
    }
    final Object this$username = this.getUsername();
    final Object other$username = other.getUsername();
    if (this$username == null ? other$username != null : !this$username.equals(other$username)) {
      return false;
    }
    final Object this$age = this.getAge();
    final Object other$age = other.getAge();
    if (this$age == null ? other$age != null : !this$age.equals(other$age)) {
      return false;
    }
    return true;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $id = this.getId();
    result = result * PRIME + ($id == null ? 43 : $id.hashCode());
    final Object $username = this.getUsername();
    result = result * PRIME + ($username == null ? 43 : $username.hashCode());
    final Object $age = this.getAge();
    result = result * PRIME + ($age == null ? 43 : $age.hashCode());
    return result;
  }

  protected boolean canEqual(Object other) {
    return other instanceof UserWithCorrectHashCode;
  }

  public String toString() {
    return "com.pswiderski.UserWithCorrectHashCode(id=" + this.getId() + ", username=" + this
        .getUsername() + ", age=" + this.getAge() + ")";
  }
}

```

In case we would like to replace generated methods, it can be done by simply overriding it in class:


```java
@Data
public class UserWithWrongHashCode implements User {

  private String id;

  private String username;

  private Integer age;

  @Override
  public int hashCode() {
    return -1;
  }
}
```

Getting back to equals() and hashCode(), testing insertion was done with usage of TestHelper class as Users objects generator.
In first case when User had correct hashCode(), inserting 20000 random Users objects produced following results:


![Users with correct hashCode insert test](https://github.com/Pszemo230/Pszemo230.github.io/blob/master/assets/usersWithCorrectHashCodeInsertionTest.PNG?raw=true "Users with correct hashCode insert test")


User with hashCode() method returning -1 results looked as follows:


![Users with wrong hashCode insert test](https://github.com/Pszemo230/Pszemo230.github.io/blob/master/assets/usersWithWrongHashCodeInsertionTest.PNG?raw=true "Users with wrong hashCode insert test")


As presented above, wrong hashCode can drastically increase time needed to insert multiple Objects to hashmap. 
Tests: mapHashCodeGetTest() for both classes also showed that if two equaly objects are returning same hashCode, it is not possibe to return one of them from hashMap.Even if we provide theoretically same object as key. 




