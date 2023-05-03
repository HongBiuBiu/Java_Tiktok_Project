<template>
  <view>
    <ss-select-city
      v-model="value"
      :hot-citys="hotCitys"
      @on-select="onSelect"
    />
  </view>
</template>

<script>
import ssSelectCity from '@/components/ss-select-city/index.vue'

export default {
  data() {
    return {
      hotCitys: [
        '北京',
        '上海'
      ],
      value: '上海'
    }
  },
  components: {
    ssSelectCity
  },
  methods: {
    onSelect(city) {
      console.log(city)
    }
  }
}
</script>

<style></style>
