```mermaid
---
title: Blackjack Player Hierarchy
---
classDiagram
    class Player {
        draw()
        canDraw()* Boolean
    }
    <<Abstract>> Player

    Player <|-- Gamer
    Player <|-- Dealer
    
    class Game {
        cardDeck CardDeck
    }

```
