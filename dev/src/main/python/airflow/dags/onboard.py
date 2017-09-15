from datetime import datetime
from airflow import DAG
from airflow.operators.dummy_operator import DummyOperator
from airflow.operators import MeetingSchedulerOperator

default_args = {
        'owner': 'airflow',
        'depends_on_past': False,
        'start_date': seven_days_ago,
        'email': ['airflow@airflow.com'],
        'email_on_failure': True,
        'email_on_retry': True,
        'retries': 1,
        'retry_delay': timedelta(minutes=5),
      )

dag = DAG('onboard_dag', description='The OnBoard DAG',
          schedule_interval='0 12 * * *',
          default_args=default_args,
          start_date=datetime(2017, 3, 20), catchup=False)

dummy_task = DummyOperator(task_id='dummy_task', dag=dag)

meeting_scheduler_task = MeetingSchedulerOperator(args_operator_param='This is a OnBoard Flux',
                                task_id='onboard_operator_task', dag=dag)

dummy_task >> meeting_scheduler_task
