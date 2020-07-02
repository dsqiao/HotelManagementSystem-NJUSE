<template>
    <div class="evaluationList">
        <div class="filter"></div>
        <div class="list">
            <a-table
                :columns="columns"
                :dataSource="evaluations"
            >
                <span slot="rate" slot-scope="text">
                    <a-rate :default-value="text" disabled />
                </span>
            </a-table>
        </div>
        <EvaluateModal></EvaluateModal>
    </div>
</template>

<script>
    import EvaluateModal from './evaluateModal'
    const columns = [
        {
            title: '用户',
            dataIndex: 'userId',
            key: 'userId',
        },
        {
            title: '评分',
            dataIndex: 'rate',
            key: 'rate',
            scopedSlots: { customRender: 'rate'}
        },
        {
            title: '评论',
            dataIndex: 'evaluation',
            key: 'evaluation',
        }
    ];
    export default {
        name: "evaluationList",
        props: {
            evaluations: {
                type: Array,
            },
        },
        data() {
            return {
                columns,
            }
        },
        components: {
            EvaluateModal
        },

    }
</script>

<style scoped>

</style>