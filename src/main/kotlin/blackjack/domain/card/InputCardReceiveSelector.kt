package blackjack.domain.card

import blackjack.view.printCardReceiveNotWant

class InputCardReceiveSelector: CardReceiveSelector {
    override fun cardReceiveNotWant(name: String): Boolean {
        return printCardReceiveNotWant(name)
    }
}
