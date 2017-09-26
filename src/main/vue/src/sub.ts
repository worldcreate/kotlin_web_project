import Vue from 'vue';
import Component from 'vue-class-component';
// Ajax通信ライブラリ
import axios from 'axios';
import Rx from 'rxjs/Rx';
import {Item} from './model/item';
import {Model} from './model/model';

@Component({
  template: `
      <div>
        <h2>{{message}}</h2>
        <p>{{count}}</p>
        <p>
          <button @click="onClick">Add +1</button>
        </p>
        <ol>
          <li v-for="item in data.items">
            {{ item.key }}:{{ item.value }}
          </li>
        </ol>
      </div>`,
  props   : ['message']
})
export default class MyComponent extends Vue {
  count = 0;
  data:Model = new Model();

  onClick() {
    this.count = this.count + 1;
    Rx.Observable.fromPromise(axios.get('/app/test'))
          .subscribe((res) => {
                console.log(res.data);
                this.data = res.data;
          });
  }
}