export default class HUD {
  constructor(scene) {
    this.scene = scene;
    this.text = scene.add.text(10, 80, 'HUD: Ativado', { fontSize: '14px', fill: '#fff' });
  }

  update(content) {
    this.text.setText(content);
  }
}