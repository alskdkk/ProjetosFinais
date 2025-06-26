
import * as Phaser from 'https://cdn.jsdelivr.net/npm/phaser@3.70.0/dist/phaser.esm.js';
import GameStats from '../utils/GameStats.js';

export default class SummaryScene extends Phaser.Scene {
  constructor() {
    super('SummaryScene');
  }

  create() {
    let stats = { score: 0, collected: 0, deaths: 0 };
    try {
      stats = GameStats.getSummary();
      console.log("Resumo solicitado:", stats);
    } catch (e) {
      console.error("Erro ao carregar GameStats:", e);
    }

    this.add.text(200, 100, '🏁 Fim de jogo!', { fontSize: '32px', fill: '#fff' });
    this.add.text(200, 160, ` Pontuação final: ${stats.score}`, { fontSize: '20px', fill: '#fff' });
    this.add.text(200, 190, ` Itens coletados: ${stats.collected}`, { fontSize: '20px', fill: '#fff' });
    this.add.text(200, 220, ` Vezes pego: ${stats.deaths}`, { fontSize: '20px', fill: '#fff' });

    const resetButton = this.add.text(200, 280, 'Reiniciar Estatísticas', {
      fontSize: '20px',
      fill: '#0f0',
      backgroundColor: '#000',
      padding: { x: 10, y: 5 }
    }).setInteractive({ useHandCursor: true });

    resetButton.on('pointerdown', () => {
      GameStats.reset();
      localStorage.removeItem('gameStats_initialized');
      this.scene.restart(); 
    });
  }
}
