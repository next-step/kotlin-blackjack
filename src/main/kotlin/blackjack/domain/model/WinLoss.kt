package blackjack.domain.model

enum class WinLoss {
    WIN, LOSS;

    companion object {

        fun winLoss(dealer: Dealer, gambler: Gambler): WinLoss {
            dealer.cards.sum().value > gambler.cards.sum().value
        }
    }

}
