async function funcProductName() {
    console.log("Hello world!");

    let response = await fetch("http://localhost:8080/api/hero-stats");
    console.log(response);
    fetch("http://localhost:8080/api/hero-stats")
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            $("#health").text("Health:"+data.stats.currentHealth+"/" + data.stats.health)
            $("#mana").text("Mana:"+data.stats.currentMana+"/"+data.stats.mana)
            $("#experience").text("Experience:"+data.experience+"/"+data.experienceNeededToLevelUp)
            $("#level").text("Level:"+data.level)
            $("#gold").text("Gold:"+data.gold)
        })
        .catch(console.error);

}

funcProductName()