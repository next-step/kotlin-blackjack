package blackjack.domain

class Game(
    players: List<Player>,
    drawer: CardDeck,
) {
    var onPlayerTurnCompleted: ((String, Card) -> Unit)? = null

    val players = players
    val drawer = drawer
    private var isGameDone = false

    fun startGame() {
        drawer.generate()

        while(isGameDone){
            startTurn()
        }

    }

    fun startTurn(){
        players.forEach { player ->
            checkPlayerIsDraw(player)
            drawCard(player)
            onPlayerTurnCompleted
        }
    }


    fun drawCard(player: Player) {
        player.drawCard(drawer.drawCard())
    }

    fun checkPlayerIsDraw(player: Player): Boolean {
        //플레이어의 카드 계산해서 21이상인지 확인
        val ret = player.calculateCard()
        return ret < 21
    }

    fun getResult(){

    }

}