import * as Phaser from 'https://cdn.jsdelivr.net/npm/phaser@3.70.0/dist/phaser.esm.js';
import Player from '../objects/Player.js';
import Collectible from '../objects/Collectible.js';
import Inventory from '../objects/Inventory.js';
import Enemy from '../objects/Enemy.js';
import NPC from '../objects/NPC.js';
import MapManager from '../objects/MapManager.js';
import HUD from '../objects/HUD.js';
import DataLogger from '../utils/DataLogger.js';
import ImageItem from '../objects/ImageItem.js';

export default class GameScene extends Phaser.Scene {
  constructor() {
    super('GameScene');
  }

  create() {
    this.mapManager = new MapManager(this);
    this.player = new Player(this, 100, 100);
    this.enemy = new Enemy(this, 400, 300, 'enemy');
    this.npc = new NPC(this, 600, 300);
    this.hud = new HUD(this);
    this.inventory = new Inventory(this);
    this.score = 0;

    this.scoreText = this.add.text(10, 30, 'Pontuação: 0', { fontSize: '14px', fill: '#fff' });

    this.physics.add.existing(this.enemy);
    this.physics.add.existing(this.npc);

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
          DataLogger.log('coleta', data.name);
        }
      });
      return sprite;
    });

    this.physics.add.overlap(this.player, this.npc, () => {
      this.npc.interact();
    });

    this.physics.add.overlap(this.player, this.enemy, () => {
      this.inventory.clear();
      this.score = 0;
      this.updateUI();
      DataLogger.log('inimigo', 'zerou inventário e pontuação');
      alert("Você foi pego! Perdeu tudo.");
      this.scene.restart();
    });

    this.input.keyboard.on('keydown-I', () => {
      this.inventoryVisible = !this.inventoryVisible;
      this.inventory.setVisible(this.inventoryVisible);
    });

    this.bgm = this.sound.add('bgm', { loop: true });
    this.input.once('pointerdown', () => {
      this.bgm.play();
    });
  }

  update() {
    this.player.update();
    this.enemy.update(this.player);
  }

  updateUI() {
    this.scoreText.setText('Pontuação: ' + this.score);
  }
}