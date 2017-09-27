# -*- coding: utf-8 -*-

import airflow
from airflow.models import DAG
from airflow.operators.dummy_operator import DummyOperator
from airflow.operators.subdag_operator import SubDagOperator

from airflow.example_dags.subdags.subdag import subdag


DAG_NAME = 'contract'

args = {
        'owner': 'airflow',
        'depends_on_past': False,
        'start_date': airflow.utils.dates.days_ago(2),
        'email': ['airflow@airflow.com'],
        'email_on_failure': True,
        'email_on_retry': True,
        'retries': 1
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
    task_id='onboard_dag',
    subdag=subdag(DAG_NAME, 'onboard_dag', args),
    default_args=args,
    dag=dag,
)

ongoing = SubDagOperator(
    task_id='ongoing_dag',
    subdag=subdag(DAG_NAME, 'ongoing_dag', args),
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
