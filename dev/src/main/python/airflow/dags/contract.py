# -*- coding: utf-8 -*-

import airflow

from airflow.models import DAG
from airflow.operators.dummy_operator import DummyOperator
from airflow.operators.subdag_operator import SubDagOperator

from airflow.example_dags.subdags.subdag import subdag


DAG_NAME = 'contract_dag'

args = {
    'owner': 'airflow',
    'start_date': airflow.utils.dates.days_ago(2),
}

dag = DAG(
    dag_id=DAG_NAME,
    default_args=args,
    schedule_interval="@once",
)

start = DummyOperator(
    task_id='start',
    default_args=args,
    dag=dag,
)

onboard = SubDagOperator(
    task_id='onboard',
    subdag=subdag(DAG_NAME, 'onboard', args),
    default_args=args,
    dag=dag,
)

ongoing = SubDagOperator(
    task_id='ongoing',
    subdag=subdag(DAG_NAME, 'ongoing', args),
    default_args=args,
    dag=dag,
)

end = DummyOperator(
    task_id='end',
    default_args=args,
    dag=dag,
)

start.set_downstream(onboard)
onboard.set_downstream(ongoing)
ongoing.set_downstream(end)
