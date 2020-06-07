---
layout: post
title: HashCode() and equals() Java Object's methods with Lombok help
date: 2017-08-30
comments: true
excerpt_separator: "<!--more-->"

---
In this post I would like to write about how bad wrong defined hashCode can affect HashMap performance.


<!--more-->

Official [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html) class Java docs says: 
> It is not required that if two objects are unequal according to the equals(java.lang.Object) method, 
> then calling the hashCode method on each of the two objects must produce distinct integer results. 
> However, the programmer should be aware that producing distinct integer results for unequal objects may improve the performance of hash tables.

We will check how much warning in docs is reasonable. 

To provide performance tests, I wrote two Junit tests. Both tests with testing classes can be accessed in [repo](https://github.com/Pszemo230/JavaExercises/tree/master/hashCodeAndEquals) as maven project.

Objects which will be inserted to a `HashMap` are `User` class but one will have wrong `hashCode()` method (returning '-1' for every object).
In order to reduce implementation time, I used [Lombok](https://projectlombok.org/) library and its `@Data` annotation.


`@Data` annotation helped in reducing lines of code in both `User` types. It automatically generated:
- all getters and setters for fields
- `toString()` method
- `equals(Object o)` method
- public constructor without parameters
- `canEqual(Object other)` method


Whole class `UserWithCorrectHashCode` code can be then written as follows:
```java

@Data
public class UserWithCorrectHashCode implements User {

  private String id;

  private String username;

  private Integer age;

}
```

In this project I used Intellij IDEA IDE which have great support after installing Lombok [plugin](https://plugins.jetbrains.com/plugin/6317-lombok-plugin).
It lets user to delombok annotations as showed below:

![Lombok plugin options](/assets/2017-08-30-HashCode-and-Equals-in-Java-and-Lombok/delombok.png?raw=true "Lombok plugin options")


After delombok `UserWithCorrectHashCode` class code looks like this:


```java
public class UserWithCorrectHashCode implements User {

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

Getting back to `equals()` and `hashCode()`, testing insertion was done with usage of `TestHelper` class as `Users` objects generator.
In first case when User had correct `hashCode()`, inserting 20000 random Users objects produced following results:


![Users with correct hashCode insert test](/assets/2017-08-30-HashCode-and-Equals-in-Java-and-Lombok/usersWithCorrectHashCodeInsertionTest.PNG?raw=true "Users with correct hashCode insert test")


User with `hashCode()` method returning -1 results looked as follows:


![Users with wrong hashCode insert test](/assets/2017-08-30-HashCode-and-Equals-in-Java-and-Lombok/usersWithWrongHashCodeInsertionTest.PNG?raw=true "Users with wrong hashCode insert test")


As presented above, wrong `hashCode()` method implementation can drastically increase time needed to insert multiple Objects to a hashmap.
 
When we look into internal `HashMap` implementation in Java Standard lib, we can see the reason.
   
Method `java.util.HashMap#putVal` looks like this:
```java
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }

```

The most important piece for us is the following: 

```java
(...)
629:        if ((p = tab[i = (n - 1) & hash]) == null)
630:            tab[i] = newNode(hash, key, value, null);
(...)
```

In this place, it is checked if there is already value in a table (storing map nodes) with an index calculated by input hashcode.

If hashcode is same for two keys, then the second key will have the same index as already stored.
To determinate if those two objects are the same keys, additional comparison must be performed.
This comparison include checking if every existing node in a map is equal to new entry key.

Invoked `equals()` method checks every field in compared objects, so it can take much time. 
The more keys are already saved, the more `equals()` checks must be done. 

Concluding, we should always implement `hashcode()` method alongside `equals()` method. 
It can greatly reduce time of operations in hash tables.
In our case of inserting 20 thousand of random objects, bad `hashcode()` implementation led to increasing insertion time by about 400%.  

