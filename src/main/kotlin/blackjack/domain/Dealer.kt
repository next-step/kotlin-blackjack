package blackjack.domain

class Dealer(val cards: Cards = initCards()) {

    companion object {
        fun initCards(): Cards {
            return Cards()
        }
    }
}
