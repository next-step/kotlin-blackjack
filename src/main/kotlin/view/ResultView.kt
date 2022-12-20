package view

class ResultView {
    fun showDistributedCard(names: List<String>) {
        for ((index, name) in names.withIndex()) {
            if (index == 0) {
                print("$name")
                continue
            }
            print(", $name")
        }
        println("에게 2장의 카드를 나누었습니다.")
    }
}
