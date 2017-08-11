import Vue from 'vue';
import Component from 'vue-class-component';
// Ajax通信ライブラリ
import axios from 'axios';
import Rx from 'rxjs/Rx';

@Component({
  template: `
      <div>
        <h2>{{message}}</h2>
        <p>{{count}}</p>
        <p>
          <button @click="onClick">Add +1</button>
        </p>
      </div>`,
  props   : ['message']
})
export default class MyComponent extends Vue {
  count = 0;

  onClick() {
    this.count = this.count + 1;
    Rx.Observable.fromPromise(axios.get('/app'))
          .subscribe((res) => {
                console.log(res.data)
          });
  }
}