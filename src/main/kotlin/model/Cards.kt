package model

class Cards(val list: List<Card>) {
    private var index: Int = 0

    fun pick(): Card{
        val card = list[index];
        index++;
        println(index)
        return card
    }
}