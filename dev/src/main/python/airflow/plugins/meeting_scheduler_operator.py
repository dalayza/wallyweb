import logging

from airflow.models import BaseOperator
from airflow.plugins_manager import AirflowPlugin
from airflow.utils.decorators import apply_defaults

log = logging.getLogger(__name__)

class MeetingSchedulerOperator(BaseOperator):

    @apply_defaults
    def __init__(self, args_operator_param, *args, **kwargs):
        self.operator_param = args_operator_param
        super(MeetingSchedulerOperator, self).__init__(*args, **kwargs)

    def execute(self, context):
        log.info("scheduling meeting... ")
        log.info('operator_param: %s', self.operator_param)

class MyFirstPlugin(AirflowPlugin):
    name = "meeting_scheduler_operator"
    operators = [MeetingSchedulerOperator]
