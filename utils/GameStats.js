
export default class GameStats {
  static load() {
    const data = JSON.parse(localStorage.getItem('gameStats'));
    if (data) {
      this.collected = data.collected || 0;
      this.deaths = data.deaths || 0;
      this.score = data.score || 0;
    } else {
      this.reset(); // fallback
    }
  }

  static save() {
    localStorage.setItem('gameStats', JSON.stringify({
      collected: this.collected,
      deaths: this.deaths,
      score: this.score
    }));
  }

  static reset() {
    this.collected = 0;
    this.deaths = 0;
    this.score = 0;
    this.save();
  }

  static addItem() {
    this.collected += 1;
    this.save();
  }

  static addScore(points) {
    this.score += points;
    this.save();
  }

  static addDeath() {
    this.deaths += 1;
    this.save();
  }

  static getSummary() {
    return {
      collected: this.collected,
      deaths: this.deaths,
      score: this.score
    };
  }
}

// Reset automático apenas uma vez
if (!localStorage.getItem('gameStats_initialized')) {
  GameStats.reset();
  localStorage.setItem('gameStats_initialized', 'true');
}

GameStats.load();
