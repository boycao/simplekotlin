// Explore a simple class

println("UW Homework: Simple Kotlin")

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(input: Any): String {
    when(input) {
        0 -> return("zero")
        1 -> return ("one")
        in 2..10 -> return("low number")
        "Hello" -> return("world")
        is String -> return("Say what?")
    }
    return("I don't understand")
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(x: Int, y: Int): Int {                                         
    return x + y
}
// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(x: Int, y: Int): Int {                                          
    return x - y
}
// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(x:Int, y:Int, operation: (Int, Int) -> Int): Int{
    return operation(x,y)
}
// write a class "Person" with first name, last name and age
class Person(val firstName: String, var lastName: String, var age: Int){
    val debugString: String
        get() = "[Person firstName:${firstName} lastName:${lastName} age:${age}]"
}

// write a class "Money"
class Money(var amount: Int, var currency: String) {
    // convert function
    fun convert(convertCurrency: String): Money {
        var convertAmount = 0
        when (currency) {
            "USD" -> when (convertCurrency) {
                "GBP" -> convertAmount = amount/2
                "USD" -> convertAmount = amount
                "EUR" -> convertAmount = 3*amount/2
                "CAN" -> convertAmount = 5*amount/4
            }

            "GBP" -> when (convertCurrency) {
                "GBP" -> convertAmount = amount
                "USD" -> convertAmount = 2*amount
                "EUR" -> convertAmount = 3*amount
                "CAN" -> convertAmount = 5*amount/2

            }

            "EUR" -> when (convertCurrency) {
                "GBP" -> convertAmount = amount/3
                "USD" -> convertAmount = 2*amount/3
                "EUR" -> convertAmount = amount
                "CAN" -> convertAmount = 5*amount/6
            }

            "CAN" -> when(convertCurrency) {
                "GBP" -> convertAmount = 5*amount/2
                "USD" -> convertAmount = amount
                "EUR" -> convertAmount = 3*amount/2
                "CAN" -> convertAmount = amount
            }
        }
        return Money(convertAmount, convertCurrency)
    }

    operator fun plus(input: Money): Money {
        var addMoney = input.convert(currency)
        return(Money(addMoney.amount + amount, currency))
    }
}
// ============ DO NOT EDIT BELOW THIS LINE =============

print("When tests: ")
val when_tests = listOf(
    "Hello" to "world",
    "Howdy" to "Say what?",
    "Bonjour" to "Say what?",
    0 to "zero",
    1 to "one",
    5 to "low number",
    9 to "low number",
    17.0 to "I don't understand"
)
for ((k,v) in when_tests) {
    print(if (whenFn(k) == v) "." else "!")
}
println("")

print("Add tests: ")
val add_tests = listOf(
    Pair(0, 0) to 0,
    Pair(1, 2) to 3,
    Pair(-2, 2) to 0,
    Pair(123, 456) to 579
)
for ( (k,v) in add_tests) {
    print(if (add(k.first, k.second) == v) "." else "!")
}
println("")

print("Sub tests: ")
val sub_tests = listOf(
    Pair(0, 0) to 0,
    Pair(2, 1) to 1,
    Pair(-2, 2) to -4,
    Pair(456, 123) to 333
)
for ( (k,v) in sub_tests) {
    print(if (sub(k.first, k.second) == v) "." else "!")
}
println("")

print("Op tests: ")
print(if (mathOp(2, 2, { l,r -> l+r} ) == 4) "." else "!")
print(if (mathOp(2, 2, ::add ) == 4) "." else "!")
print(if (mathOp(2, 2, ::sub ) == 0) "." else "!")
print(if (mathOp(2, 2, { l,r -> l*r} ) == 4) "." else "!")
println("")


print("Person tests: ")
val p1 = Person("Ted", "Neward", 47)
print(if (p1.firstName == "Ted") "." else "!")
p1.age = 48
print(if (p1.debugString == "[Person firstName:Ted lastName:Neward age:48]") "." else "!")
println("")

print("Money tests: ")
val tenUSD = Money(10, "USD")
val twelveUSD = Money(12, "USD")
val fiveGBP = Money(5, "GBP")
val fifteenEUR = Money(15, "EUR")
val fifteenCAN = Money(15, "CAN")
val convert_tests = listOf(
    Pair(tenUSD, tenUSD),
    Pair(tenUSD, fiveGBP),
    Pair(tenUSD, fifteenEUR),
    Pair(twelveUSD, fifteenCAN),
    Pair(fiveGBP, tenUSD),
    Pair(fiveGBP, fifteenEUR)
)
for ( (from,to) in convert_tests) {
    print(if (from.convert(to.currency).amount == to.amount) "." else "!")
}
val moneyadd_tests = listOf(
    Pair(tenUSD, tenUSD) to Money(20, "USD"),
    Pair(tenUSD, fiveGBP) to Money(20, "USD"),
    Pair(fiveGBP, tenUSD) to Money(10, "GBP")
)
for ( (pair, result) in moneyadd_tests) {
    print(if ((pair.first + pair.second).amount == result.amount &&
              (pair.first + pair.second).currency == result.currency) "." else "!")
}
println("")
