export default class Inventory {
  constructor(scene) {
    this.scene = scene;
    this.items = [];
    this.icons = [];
  }

  add(item) {
    this.items.push(item);
    console.log('Adicionado ao inventário:', item.name);
    const icon = this.scene.add.image(10 + this.items.length * 40, 550, item.texture).setScale(1.5);
    icon.setScrollFactor(0);
    this.icons.push(icon);
  }

  getAll() {
    return this.items.map(i => i.name);
  }

  clear() {
    this.items = [];
    this.icons.forEach(icon => icon.destroy());
    this.icons = [];
    console.log('Inventário limpo.');
  }

  setVisible(visible) {
    this.icons.forEach(icon => icon.setVisible(visible));
  }
}