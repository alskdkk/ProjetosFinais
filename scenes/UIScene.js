import * as Phaser from 'https://cdn.jsdelivr.net/npm/phaser@3.70.0/dist/phaser.esm.js';

export default class UIScene extends Phaser.Scene {
  constructor() {
    super('UIScene');
  }

  create() {
    this.add.text(10, 40, 'HUD: Em construção', { fontSize: '14px', fill: '#fff' });
  }
}