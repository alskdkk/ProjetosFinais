import * as Phaser from 'https://cdn.jsdelivr.net/npm/phaser@3.70.0/dist/phaser.esm.js';

export default class BootScene extends Phaser.Scene {
  constructor() {
    super('BootScene');
  }

  preload() {
    this.load.image('player', 'assets/images/player.png');
    this.load.image('enemy', 'assets/images/enemy.png');
    this.load.image('item1', 'assets/images/item1.png');
    this.load.image('item2', 'assets/images/item2.png');
    this.load.image('item3', 'assets/images/item3.png');
    this.load.image('item4', 'assets/images/item4.png');
    this.load.audio('bgm', 'assets/audio/background.mp3');
    this.load.audio('collect', 'assets/audio/collect.mp3');
    this.load.audio('hit', 'assets/audio/hit.mp3');
  }

  create() {
    this.scene.start('Level1');
  }
}