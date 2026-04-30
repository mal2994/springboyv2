# Purpose

Spring boy over internet baby!

# Build and Run Locally

```bash
mvn clean package -DskipTests
mvn spring-boot:run
# Test ping
curl http://localhost:8080/api/ping
# Test game (PNG)
curl http://localhost:8080/api/game -o junk/game.png
du -h game.png
```

# Build and Deploy on Railway

Any time you push it is gonna trigger railway and probably cost me 1 penny. so test locally and then push when you have something big.

```bash
git add .
git commit -m "test msg"
git push origin main
```
