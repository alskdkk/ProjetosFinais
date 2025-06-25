import ImageItem from '../objects/ImageItem.js';

export default class ItemFactory {
  static create(name, texture) {
    return new ImageItem(name, texture);
  }
}