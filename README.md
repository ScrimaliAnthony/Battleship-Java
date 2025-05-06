<div align="right">
    <img src="https://img.shields.io/github/v/release/ScrimaliAnthony/Battleship-java" />
    <img src="https://img.shields.io/github/stars/ScrimaliAnthony/Battleship-java?style=social" />
    <img src="https://visitor-badge.laobi.icu/badge?page_id=ScrimaliAnthony.Battleship-java" />
</div>

<img src="./img.png"/>



---

Bienvenue dans **Battleship Java**, un jeu de bataille navale en ligne de commande pour deux joueurs, développé en Java. Préparez votre flotte, planifiez vos tirs... et que le meilleur stratège l'emporte !


## 🎮 Fonctionnalités

* ⚔️ Jeu à 2 joueurs en local, avec alternance de tours
* 🌫 Brouillard de guerre pour masquer la flotte ennemie
* 🛠 Placement des navires avec détection d'erreurs (chevauchement, bordure, mauvaise longueur...)
* 💥 Système de tir intelligent avec retour visuel (coulé, touché, manqué)
* 📢 Messages d'erreurs clairs grâce à des exceptions personnalisées

---

## 📦 Télécharger le jeu

⭐ Pensez à mettre une étoile au projet si vous l'appréciez !

👉 Cliquez ici pour récupérer la dernière version prête à jouer :

**➡️ [Battleship.jar – Version 1.0.0](https://github.com/ScrimaliAnthony/battleship-java/releases/latest)**

---

## ▶️ Comment jouer

1. Assurez-vous que Java est installé :

```bash
java -version
```

2. Lancez le jeu depuis un terminal avec :

```bash
java -jar Battleship.jar
```

3. Suivez les instructions à l'écran pour placer votre flotte, passer le tour et attaquer votre adversaire.

💡 **Astuce :** utilisez des majuscules ou minuscules, le jeu accepte les deux !

---

## 💠 Pour les développeurs

### 🗂 Structure du projet :

```
src/
├── battleship/app/         → Lancement du jeu (Main.java)
├── battleship/model/       → Modèle (Ship, Player, Board...)
├── battleship/utils/       → Conversions et validations
├── battleship/ui/          → Affichage console
└── battleship/exceptions/  → Exceptions personnalisées
```

### 🔨 Compilation manuelle :

```bash
javac -d out $(find src -name "*.java")
jar cfm Battleship.jar manifest.txt -C out .
```

---
