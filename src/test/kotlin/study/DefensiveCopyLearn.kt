package study

import blackjack.model.Card
import blackjack.model.Cards
import blackjack.view.Console.present
import io.kotest.core.spec.style.StringSpec

class DefensiveCopyLearn : StringSpec({
    "sdf" {

        val list = mutableListOf<Card>()
        list.add(Card.of())
        list.add(Card.of())
        list.add(Card.of())
        val cards = Cards(list)
        println("BEFORE : ${cards.present()}")
        list.clear()
        println("AFTER : ${cards.present()}")
    }
})
