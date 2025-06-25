import * as Phaser from 'https://cdn.jsdelivr.net/npm/phaser@3.70.0/dist/phaser.esm.js';
import Player from '../objects/Player.js';
import Collectible from '../objects/Collectible.js';
import Inventory from '../objects/Inventory.js';
import Enemy from '../objects/Enemy.js';
import NPC from '../objects/NPC.js';
import HUD from '../objects/HUD.js';
import GameStats from '../utils/GameStats.js';
import MapManager from '../objects/MapManager.js';
import DataLogger from '../utils/DataLogger.js';
import ImageItem from '../objects/ImageItem.js';

export default class Level3 extends Phaser.Scene {
  constructor() {
    super('Level3');
  }

  init(data) {
    this.score = data.score || 0;
    this.level = 3;
  }

  create() {
    this.mapManager = new MapManager(this);
    this.player = new Player(this, 100, 100);
    this.inventory = new Inventory(this);
    this.hud = new HUD(this);
    this.npc = new NPC(this, 600, 300);
    this.inventoryVisible = true;

    this.scoreText = this.add.text(10, 30, 'Pontuação: ' + this.score, { fontSize: '14px', fill: '#fff' });
    this.inventoryText = this.add.text(10, 60, '', { fontSize: '14px', fill: '#fff' });

    const itemData = [
      { x: 200, y: 200, name: 'item1', texture: 'item1' },
      { x: 250, y: 300, name: 'item2', texture: 'item2' },
      { x: 300, y: 400, name: 'item3', texture: 'item3' },
      { x: 350, y: 250, name: 'item4', texture: 'item4' }
    ];

    this.items = itemData.map(data => {
      const sprite = new Collectible(this, data.x, data.y, data.texture);
      sprite.collected = false;
      this.physics.add.overlap(this.player, sprite, () => {
        if (!sprite.collected) {
          sprite.collect();
          sprite.collected = true;
          const itemObj = new ImageItem(data.name, data.texture);
          this.inventory.add(itemObj);
          this.score += 10;
          this.updateUI();
          this.sound.play('collect');
          DataLogger.log('coleta', data.name);
        }
      });
      sprite.on('collected', () => {
        GameStats.addItem();
        GameStats.addScore(10);
      });
      return sprite;
    });

    this.physics.add.overlap(this.player, this.npc, () => {
      this.npc.interact();
    });

    this.enemies = [];
    for (let i = 0; i < this.level; i++) {
      const enemy = new Enemy(this, 400 + i * 50, 300 + i * 30, 'enemy');
      this.physics.add.existing(enemy);
      this.enemies.push(enemy);
      this.physics.add.overlap(this.player, enemy, () => {
        this.inventory.clear();
        this.score = 0;
        this.updateUI();
        this.sound.play("hit");
        alert("Você foi pego! Reiniciando fase...");
        GameStats.addDeath();
        this.scene.restart({ score: 0 });
      });
    }

    this.input.keyboard.on('keydown-I', () => {
      this.inventoryVisible = !this.inventoryVisible;
      this.inventory.setVisible(this.inventoryVisible);
    });

    this.input.keyboard.on('keydown-N', () => {
      alert("Avançando para a próxima fase!");
      this.scene.start('SummaryScene');
    });

    this.bgm = this.sound.add('bgm', { loop: true });
    this.input.once('pointerdown', () => {
      this.bgm.play();
    });
  }

  update() {
    this.player.update();
    this.enemies.forEach(enemy => enemy.update(this.player));
  }

  updateUI() {
    this.scoreText.setText('Pontuação: ' + this.score);
  }
}