package study

import blackjack.model.Card
import blackjack.model.Cards
import blackjack.model.pack.ShuffledPack
import blackjack.view.Console.present
import io.kotest.core.spec.style.StringSpec

class DefensiveCopyLearn : StringSpec({
    "sdf" {

        val list = mutableListOf<Card>()
        list.add(ShuffledPack.pickCard())
        list.add(ShuffledPack.pickCard())
        list.add(ShuffledPack.pickCard())
        val cards = Cards(list)
        println("BEFORE : ${cards.present()}")
        list.clear()
        println("AFTER : ${cards.present()}")
    }
})
